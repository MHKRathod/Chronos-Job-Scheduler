import { useEffect, useState } from "react";
import API from "../services/api";

function JobLogs() {

    const [logs, setLogs] = useState([]);

    const fetchLogs = async () => {

        try {

            const response = await API.get("/logs");

            setLogs(response.data);

        } catch (error) {

            console.error("Error fetching logs", error);
        }
    };

    useEffect(() => {

        fetchLogs();

        const interval = setInterval(fetchLogs, 5000);

        return () => clearInterval(interval);

    }, []);

    const deleteLogs = async () => {

    try {

        await API.delete("/logs");

        fetchLogs();

    } catch (error) {

        console.error("Error deleting logs", error);
    }
};

   return (

    <div className="bg-white shadow-lg rounded-xl p-6 overflow-auto">

         <h2 className="text-2xl font-bold bg-gradient-to-r from-purple-700 to-pink-600 text-white px-6 py-3 rounded-xl shadow-md">
            Execution Logs
        </h2>

        <table className="w-full border-collapse">

            <thead>

                <tr className="bg-gray-200">

                    <th className="p-3">Job ID</th>
                    <th className="p-3">Status</th>
                    <th className="p-3">Message</th>
                    <th className="p-3">Execution Time</th>

                </tr>

            </thead>

            <tbody>

                {logs.map((log) => (

                    <tr
                        key={log.id}
                        className="border-b text-center"
                    >

                        <td className="p-3">{log.jobId}</td>

                        <td className="p-3 font-bold">
                            {log.status}
                        </td>

                        <td className="p-3">{log.message}</td>

                        <td className="p-3">
                            {log.executionTime}
                        </td>

                    </tr>

                ))}

            </tbody>

             <button
        onClick={deleteLogs}
        className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
    >
        Clear Logs
    </button>

        </table>

    </div>
);
}

export default JobLogs;