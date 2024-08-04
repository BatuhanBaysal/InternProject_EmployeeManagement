import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import SalaryService from '../service/SalaryService';
import EmployeeService from '../service/EmployeeService'; 
import { format } from 'date-fns';

const AddSalaryComponent = () => {
    const [employeeId, setEmployeeId] = useState(""); 
    const [salary, setSalary] = useState("");
    const [startDate, setStartDate] = useState("");
    const [finishDate, setFinishDate] = useState("");
    const [employee, setEmployee] = useState(null); 
    const [minStartDate, setMinStartDate] = useState(""); 
    const [minFinishDate, setMinFinishDate] = useState(""); 
    const navigate = useNavigate();
    const { id } = useParams();

    const salaryData = { 
        employee, 
        salary, 
        startDate, 
        finishDate 
    };

    function saveSalary(e) {
        e.preventDefault();
        if (employee && salary !== "" && startDate !== "" && finishDate !== "") {
            const start = new Date(startDate);
            const finish = new Date(finishDate);

            if (finish < start) {
                alert("Finish date must be after the start date.");
                return;
            }

            if (finish < new Date(start.getTime() + 28 * 24 * 60 * 60 * 1000)) {
                alert("Finish date must be at least 28 days after the start date.");
                return;
            }

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
        return id ? "Update Salary" : "Add Salary";
    }

    useEffect(() => {
        const today = new Date();
        const formattedToday = format(today, 'yyyy-MM-dd');
        setMinStartDate(formattedToday);

        const initialFinishDate = new Date(today);
        initialFinishDate.setDate(today.getDate() + 28);
        const formattedInitialFinishDate = format(initialFinishDate, 'yyyy-MM-dd');
        setMinFinishDate(formattedInitialFinishDate);

        if (id) {
            SalaryService.getSalaryById(id)
                .then(res => {
                    const { employee, salary, startDate, finishDate } = res.data;
                    setEmployee(employee); 
                    setSalary(salary || "");
                    setStartDate(startDate ? format(new Date(startDate), 'yyyy-MM-dd') : formattedToday);
                    setFinishDate(finishDate ? format(new Date(finishDate), 'yyyy-MM-dd') : formattedInitialFinishDate);
                })
                .catch(e => console.log(e));
        }
    }, [id]);

    useEffect(() => {
        if (employeeId) {
            EmployeeService.getEmployeeById(employeeId)
                .then(res => setEmployee(res.data))
                .catch(e => console.log(e));
        }
    }, [employeeId]);

    const handleStartDateChange = (e) => {
        const value = e.target.value;
        setStartDate(value);
        
        const start = new Date(value);
        const finish = new Date(start);
        finish.setDate(start.getDate() + 28);
        setFinishDate(format(finish, 'yyyy-MM-dd'));

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
                        <h2 className='text-center'>{tile()}</h2>
                        <div className='card-body'>
                            <form>
                                <div className='form-group mb-2'>
                                    <input 
                                        className='form-control'
                                        value={employeeId}
                                        onChange={(e) => setEmployeeId(e.target.value)} 
                                        type="number" 
                                        placeholder='Enter Employee ID' 
                                    />
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

export default AddSalaryComponent;