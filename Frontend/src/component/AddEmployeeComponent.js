import React, { useState, useEffect } from 'react';
import EmployeeService from '../service/EmployeeService';
import { Link, useNavigate, useParams } from 'react-router-dom';

const AddEmployeeComponent = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    const employeeData = { firstName, lastName, email };

    function saveEmployee(e) {
        e.preventDefault();
        if (employeeData.firstName !== "" && employeeData.lastName !== "" && employeeData.email !== "") {
            if (validateEmail(email)) {
                if (id) {
                    EmployeeService.updateEmployee(id, employeeData)
                        .then(() => navigate("/employee"))
                        .catch(e => console.log(e));
                } else {
                    EmployeeService.saveEmployee(employeeData)
                        .then(() => navigate("/employee"))
                        .catch(e => console.log(e));
                }
            } else {
                setError("Email must be in lowercase.");
            }
        } else {
            alert("Please, fill in all inputs.");
        }
    }

    function tile() {
        return id ? "Update Employee" : "Add Employee";
    }

    function validateEmail(email) {
        const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
        return emailPattern.test(email);
    }

    useEffect(() => {
        if (id) {
            EmployeeService.getEmployeeById(id)
                .then(res => {
                    setFirstName(res.data.firstName);
                    setLastName(res.data.lastName);
                    setEmail(res.data.email);
                })
                .catch(e => console.log(e));
        }
    }, [id]);

    const handleEmailChange = (e) => {
        const value = e.target.value.toLowerCase(); 
        setEmail(value);
    };

    return (
        <div>
            <div className='container mt-5'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3'>
                        <h2 className='text-center'>{tile()}</h2>
                        <div className='card-body'>
                            <form>
                                <div className='form-group mb-2'>
                                    <input
                                        className='form-control'
                                        value={firstName}
                                        onChange={(e) => setFirstName(e.target.value)}
                                        type="text"
                                        placeholder='Enter First Name'
                                    />
                                </div>
                                <div className='form-group mb-2'>
                                    <input
                                        className='form-control'
                                        value={lastName}
                                        onChange={(e) => setLastName(e.target.value)}
                                        type="text"
                                        placeholder='Enter Last Name'
                                    />
                                </div>
                                <div className='form-group mb-2'>
                                    <input
                                        className='form-control'
                                        value={email}
                                        onChange={handleEmailChange}
                                        type="email"
                                        placeholder='Enter Email'
                                    />
                                    {error && <div className='text-danger'>{error}</div>}
                                </div>
                                <button onClick={saveEmployee} className='btn btn-success'>Save</button> {" "}
                                <Link to={"/employee"} className='btn btn-danger'>Cancel</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddEmployeeComponent;
