import React, { useState } from "react";
import axios from "axios";

const UseTicket = () => {
  const [ticketId, setTicketId] = useState("");
  const [station, setStation] = useState("");
  const [action, setAction] = useState("");
  const [response, setResponse] = useState("");
  const [error, setError] = useState("");

  const handleUseTicket = async () => {
    try {
      const result = await axios.post(
        `http://localhost:8080/api/tickets/use/${ticketId}?station=${station}&action=${action}`
      );
      setResponse(result.data);
      setError("");
    } catch (err) {
      setError(err.response?.data || "Failed to use ticket.");
      setResponse("");
    }
  };

  return (
    <div>
      <h2>Use Ticket</h2>
      <div>
        <label>
          Ticket ID:
          <input
            type="text"
            value={ticketId}
            onChange={(e) => setTicketId(e.target.value)}
          />
        </label>
      </div>
      <div>
        <label>
          Station:
          <input
            type="text"
            value={station}
            onChange={(e) => setStation(e.target.value)}
          />
        </label>
      </div>
      <div>
        <label>
          Action (entry/exit):
          <input
            type="text"
            value={action}
            onChange={(e) => setAction(e.target.value)}
          />
        </label>
      </div>
      <button onClick={handleUseTicket}>Use Ticket</button>
      {response && <p style={{ color: "green" }}>{response}</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default UseTicket;
