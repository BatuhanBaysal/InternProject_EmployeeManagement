import axios from "axios";

const BASE_URL = "http://localhost:8080/salary";
class SalaryService {
    
    getAllSalary(){
        return axios.get(BASE_URL);
    }
    
    saveSalary(salaryData){
        return axios.post(BASE_URL, salaryData);
    }

    updateSalary(id, salaryData){
        return axios.put(`${BASE_URL}/${id}`, salaryData)
    }

    getSalaryById(id){
        return axios.get(`${BASE_URL}/${id}`);
    }

    deleteSalary(id){
        return axios.delete(BASE_URL +"/" +id);
    }
}

export default new SalaryService();