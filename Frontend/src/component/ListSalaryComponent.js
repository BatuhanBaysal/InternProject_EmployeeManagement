import React, { useEffect, useState } from 'react';
import { Link} from 'react-router-dom';
import SalaryService from '../service/SalaryService';
import { format } from 'date-fns';

const EmployeeSalaryComponent = () => {
    const [salaryArray, setSalaryArray] = useState([]);

    useEffect(() => {
        getAllSalary();
    }, []);

    function getAllSalary() {
        SalaryService.getAllSalary()
            .then(res => setSalaryArray(res.data))
            .catch(e => console.log(e));
    }
    
    function formatDate(dateString) {
        if (!dateString) return "N/A";
        return format(new Date(dateString), 'yyyy-MM-dd');
    }

    return (
        <div className='container'>
            <div className='button-container'>
                <Link to={"/add-salary"} className='custom-blue-btn mb-2 mt-3'>Add Salary</Link>
                <Link to={"/employee"} className='btn btn-primary mb-2 mt-3' href="">List Employee</Link>
            </div>
            <h2 className='text-center mb-4'>List Employees Salary</h2>
            <table className='table table-bordered table striped'>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee</th>
                        <th>Employee Salary</th>
                        <th>Employee Start Date</th>
                        <th>Employee Finish Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {salaryArray.map(salary =>
                        <tr key={salary.id}>
                            <td>{salary.employee ? salary.employee.id : "N/A"}</td>
                            <td>{salary.employee ? `${salary.employee.firstName} ${salary.employee.lastName}` : "N/A"}</td>
                            <td>${salary.salary}</td>
                            <td>{formatDate(salary.startDate)}</td>
                            <td>{formatDate(salary.finishDate)}</td>
                            <td>
                            <Link to={`/employee-salary/update-salary/${salary.id}`} className='btn btn-info' href="">Update</Link> {" "}</td>
                        </tr>)}
                </tbody>
            </table>
        </div>
    )
}

export default EmployeeSalaryComponent;