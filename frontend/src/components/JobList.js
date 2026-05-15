import { useEffect, useState } from "react";
import API from "../services/api";

function JobList() {

    const [jobs, setJobs] = useState([]);

    const fetchJobs = async () => {

        try {

            const response = await API.get("");

            setJobs(response.data);

        } catch (error) {

            console.error("Error fetching jobs", error);
        }
    };

    useEffect(() => {

        fetchJobs();

        // auto refresh every 5 sec
        const interval = setInterval(fetchJobs, 5000);

        return () => clearInterval(interval);

    }, []);

    const deleteJob = async (id) => {

        try {

            await API.delete(`/${id}`);

            fetchJobs();

        } catch (error) {

            console.error("Error deleting job", error);
        }
    };

  return (

    <div className="bg-white shadow-lg rounded-xl p-6 overflow-auto">

        <h2 className="text-2xl font-bold bg-gradient-to-r from-green-600 to-emerald-500 text-white px-6 py-3 rounded-xl shadow-md">
            Job List
        </h2>

        <table className="w-full border-collapse">

            <thead>

                <tr className="bg-gray-200">

                    <th className="p-3">ID</th>
                    <th className="p-3">Name</th>
                    <th className="p-3">Status</th>
                    <th className="p-3">Scheduled Time</th>
                    <th className="p-3">Retry</th>
                    <th className="p-3">Action</th>

                </tr>

            </thead>

            <tbody>

                {jobs.map((job) => (

                    <tr
                        key={job.id}
                        className="border-b text-center"
                    >

                        <td className="p-3">{job.id}</td>

                        <td className="p-3">{job.name}</td>

                        <td
                            className={`p-3 font-bold
                            ${
                                job.status === "COMPLETED"
                                    ? "text-green-600"
                                    : job.status === "FAILED"
                                    ? "text-red-600"
                                    : job.status === "RUNNING"
                                    ? "text-blue-600"
                                    : "text-orange-500"
                            }`}
                        >
                            {job.status}
                        </td>

                        <td className="p-3">
                            {job.scheduledTime}
                        </td>

                        <td className="p-3">
                            {job.retryCount}
                        </td>

                        <td className="p-3">

                            <button
                                onClick={() => deleteJob(job.id)}
                                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                            >
                                Delete
                            </button>

                        </td>

                    </tr>

                ))}

            </tbody>

        </table>

    </div>
);
}

export default JobList;