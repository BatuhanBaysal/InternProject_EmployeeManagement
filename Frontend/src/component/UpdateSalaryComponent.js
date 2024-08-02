import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import SalaryService from '../service/SalaryService';
import EmployeeService from '../service/EmployeeService'; 
import { format } from 'date-fns';

const UpdateSalaryComponent = () => {
    const [employeeId, setEmployeeId] = useState(""); 
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [salary, setSalary] = useState("");
    const [startDate, setStartDate] = useState("");
    const [finishDate, setFinishDate] = useState("");
    const navigate = useNavigate();
    const { id } = useParams();

    const salaryData = { 
        employee: { id: employeeId }, 
        salary, 
        startDate, 
        finishDate 
    };

    function saveSalary(e) {
        e.preventDefault();
        if (employeeId && salary !== "" && startDate !== "" && finishDate !== "") {
            SalaryService.updateSalary(id, salaryData)
                .then(() => navigate("/employee-salary"))
                .catch(e => console.log(e));
        } else {
            alert("Please, fill in all inputs");
        }
    }

    useEffect(() => {
        if (id) {
            SalaryService.getSalaryById(id)
                .then(res => {
                    const { employee, salary, startDate, finishDate } = res.data;
                    setEmployeeId(employee?.id || "");
                    setSalary(salary || "");
                    setStartDate(startDate ? format(new Date(startDate), 'yyyy-MM-dd') : "");
                    setFinishDate(finishDate ? format(new Date(finishDate), 'yyyy-MM-dd') : "");

                    if (employee?.id) {
                        EmployeeService.getEmployeeById(employee.id)
                            .then(res => {
                                setFirstName(res.data.firstName);
                                setLastName(res.data.lastName);
                            })
                            .catch(e => console.log(e));
                    }
                })
                .catch(e => console.log(e));
        }
    }, [id]);

    return (
        <div>
            <div className='container mt-5'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3'>
                        <h2 className='text-center'>Update Salary</h2>
                        <div className='card-body'>
                            <form>
                                <div className='form-group mb-2'>
                                    <label className='form-control'>
                                        <b>Employee ID: {employeeId}</b>
                                    </label>
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-control'>
                                        <b>First Name: {firstName}</b>
                                    </label>
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-control'>
                                        <b>Last Name: {lastName}</b>
                                    </label>
                                </div>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        value={salary}
                                        onChange={(e) => setSalary(e.target.value)}
                                        type="number" placeholder='Enter Salary Value' />
                                </div>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        value={startDate}
                                        onChange={(e) => setStartDate(e.target.value)}
                                        type="date" placeholder='Enter Start Date' />
                                </div>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        value={finishDate}
                                        onChange={(e) => setFinishDate(e.target.value)}
                                        type="date" placeholder='Enter Finish Date' />
                                </div>
                                <button onClick={saveSalary} className='btn btn-success'>Save</button> {" "}
                                <Link to={"/employee-salary"} className='btn btn-danger'>Cancel</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateSalaryComponent;