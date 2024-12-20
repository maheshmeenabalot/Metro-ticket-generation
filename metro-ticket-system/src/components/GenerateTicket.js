import React, { useState } from "react";
import axios from "axios";

const GenerateTicket = () => {
  const [startStation, setStartStation] = useState("");
  const [endStation, setEndStation] = useState("");
  const [ticket, setTicket] = useState(null);
  const [error, setError] = useState("");

  const handleGenerateTicket = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/tickets/generate", {
        startStation,
        endStation,
      });
      setTicket(response.data);
      setError("");
    } catch (err) {
      setError(err.response?.data || "Failed to generate ticket.");
      setTicket(null);
    }
  };

  return (
    <div>
      <h2>Generate Ticket</h2>
      <div>
        <label>
          Start Station:
          <input
            type="text"
            value={startStation}
            onChange={(e) => setStartStation(e.target.value)}
          />
        </label>
      </div>
      <div>
        <label>
          End Station:
          <input
            type="text"
            value={endStation}
            onChange={(e) => setEndStation(e.target.value)}
          />
        </label>
      </div>
      <button onClick={handleGenerateTicket}>Generate Ticket</button>
      {ticket && (
        <div>
          <h3>Ticket Generated</h3>
          <p>Ticket ID: {ticket.id}</p>
          <p>Price: {ticket.price}</p>
          <p>Expiry Time: {ticket.expiryTime}</p>
        </div>
      )}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default GenerateTicket;
