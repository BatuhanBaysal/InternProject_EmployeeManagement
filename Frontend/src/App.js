import AddEmployeeComponent from "./component/AddEmployeeComponent";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HeaderComponent from "./component/HeaderComponent";
import FooterComponent from "./component/FooterComponent";
import ListEmployeeComponent from "./component/ListEmployeeComponent";
import ListSalaryComponent from "./component/ListSalaryComponent";
import AddSalaryComponent from "./component/AddSalaryComponent";
import UpdateSalaryComponent from "./component/UpdateSalaryComponent";

function App() {
  return (
    <BrowserRouter>
      <HeaderComponent />
      <div className="container">
        <Routes>
          <Route path="/" element={<ListEmployeeComponent />} />
          <Route path="/employee" element={<ListEmployeeComponent />} />
          <Route path="/add-employee" element={<AddEmployeeComponent />} />
          <Route path="/add-employee/:id" element={<AddEmployeeComponent />} />
          <Route path="/employee-salary" element={<ListSalaryComponent />} />
          <Route path="/employee-salary/:id" element={<ListSalaryComponent />} />
          <Route path="/add-salary" element={<AddSalaryComponent />} />
          <Route path="/add-salary/:id" element={<AddSalaryComponent />} />
          <Route path="/employee-salary/update-salary" element={<UpdateSalaryComponent />} />
          <Route path="/employee-salary/update-salary/:id" element={<UpdateSalaryComponent />} />
        </Routes>
      </div>
      <FooterComponent />
    </BrowserRouter>
  );
}

export default App;