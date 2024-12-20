import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import GenerateTicket from "./components/GenerateTicket";
import UseTicket from "./components/UseTicket";

function App() {
  return (
    <Router>
      <div className="container">
        <h1>Metro Ticket Booking System</h1>
        <Routes>
          <Route path="/" element={<GenerateTicket />} />
          <Route path="/use-ticket" element={<UseTicket />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
