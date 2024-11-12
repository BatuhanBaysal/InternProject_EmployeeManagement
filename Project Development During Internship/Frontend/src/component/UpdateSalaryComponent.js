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
    const [minStartDate, setMinStartDate] = useState("");
    const [minFinishDate, setMinFinishDate] = useState("");
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
            const start = new Date(startDate);
            const finish = new Date(finishDate);

            // Check if finishDate is before startDate
            if (finish < start) {
                alert("Finish date must be after the start date.");
                return;
            }

            // Check if finishDate is at least 28 days after startDate
            if (finish < new Date(start.getTime() + 28 * 24 * 60 * 60 * 1000)) {
                alert("Finish date must be at least 28 days after the start date.");
                return;
            }

            SalaryService.updateSalary(id, salaryData)
                .then(() => navigate("/employee-salary"))
                .catch(e => console.log(e));
        } else {
            alert("Please, fill in all inputs");
        }
    }

    useEffect(() => {
        const today = new Date();
        const formattedToday = format(today, 'yyyy-MM-dd');
        setMinStartDate(formattedToday);

        // Initialize finishDate to 28 days after today
        const initialFinishDate = new Date(today);
        initialFinishDate.setDate(today.getDate() + 28);
        const formattedInitialFinishDate = format(initialFinishDate, 'yyyy-MM-dd');
        setMinFinishDate(formattedInitialFinishDate);

        if (id) {
            SalaryService.getSalaryById(id)
                .then(res => {
                    const { employee, salary, startDate, finishDate } = res.data;
                    setEmployeeId(employee?.id || "");
                    setSalary(salary || "");
                    setStartDate(startDate ? format(new Date(startDate), 'yyyy-MM-dd') : formattedToday);
                    setFinishDate(finishDate ? format(new Date(finishDate), 'yyyy-MM-dd') : formattedInitialFinishDate);

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

    const handleStartDateChange = (e) => {
        const value = e.target.value;
        setStartDate(value);
        
        // Update finishDate based on new startDate
        const start = new Date(value);
        const finish = new Date(start);
        finish.setDate(start.getDate() + 28); // Set finishDate to 28 days after startDate
        setFinishDate(format(finish, 'yyyy-MM-dd'));

        // Update minFinishDate to be at least 28 days after the new startDate
        const minFinish = new Date(start);
        minFinish.setDate(start.getDate() + 28);
        setMinFinishDate(format(minFinish, 'yyyy-MM-dd'));
    };

    const handleFinishDateChange = (e) => {
        const value = e.target.value;
        setFinishDate(value);
    };

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
                                        onChange={handleStartDateChange}
                                        type="date"
                                        min={minStartDate}
                                        placeholder='Enter Start Date' />
                                </div>
                                <div className='form-group mb-2'>
                                    <input className='form-control'
                                        value={finishDate}
                                        onChange={handleFinishDateChange}
                                        type="date"
                                        min={minFinishDate}
                                        placeholder='Enter Finish Date' />
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