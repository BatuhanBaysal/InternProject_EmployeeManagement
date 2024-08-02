import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import SalaryService from '../service/SalaryService';
import { format } from 'date-fns';

const AddSalaryComponent = () => {
    const [employee_id, setEmployeeId] = useState("");
    const [salary, setSalary] = useState("");
    const [startDate, setStartDate] = useState("");
    const [finishDate, setFinishDate] = useState("");
    const navigate = useNavigate();
    const { id } = useParams();

    const salaryData = { employee_id, salary, startDate, finishDate };

    function saveSalary(e) {
        e.preventDefault();
        if (salaryData.employee_id !== "" && salaryData.salary !== "" && salaryData.startDate !== "" && salaryData.finishDate !== "") {
            if (id) {
                SalaryService.updateSalary(id, salaryData)
                    .then(() => navigate("/employee-salary"))
                    .catch(e => console.log(e));
            } else {
                SalaryService.saveSalary(salaryData)
                    .then(() => navigate("/employee-salary"))
                    .catch(e => console.log(e));
            }
        } else {
            alert("Please, fill in all inputs");
        }
    }

    function tile() {
        if (id) {
            return "Update Salary";
        } else {
            return "Add Salary";
        }
    }

    useEffect(() => {
        if (id) {
            SalaryService.getSalaryById(id)
                .then(res => {
                    //const { employee, salary, startDate, finishDate } = res.data;
                    setEmployeeId(res.data.employee?.id || "");
                    setSalary(res.data.salary || "");
                    setStartDate(res.data.startDate ? format(new Date(startDate), 'yyyy-MM-dd') : "");
                    setFinishDate(res.data.finishDate ? format(new Date(finishDate), 'yyyy-MM-dd') : "");
                })
                .catch(e => console.log(e));
        }
    }, [id]);

    return (
        <div>
            <div className='container mt-5'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3'>
                        <h2 className='text-center'>{tile()}</h2>
                        <div className='card-body'>
                            <form>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        id='employeeId'  
                                        name='employeeId'  
                                        value={employee_id}
                                        onChange={(e) => setEmployeeId(e.target.value)}
                                        type="number" placeholder='Enter Employee ID' />
                                </div>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        value={salary}
                                        onChange={(e) => setSalary(e.target.value)}
                                        type="text" placeholder='Enter Salary Value' />
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
                                <button onClick={(e) => saveSalary(e)} className='btn btn-success'>Save</button> {" "}
                                <Link to={"/employee-salary"} className='btn btn-danger' href="">Cancel</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddSalaryComponent