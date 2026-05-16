import { useEffect, useState } from "react";
import API from "../services/api";

function FailedNotifications() {

    const [failedJobs, setFailedJobs] = useState([]);

    const fetchFailedJobs = async () => {

        try {

            const response = await API.get("/jobs/failed");

            setFailedJobs(response.data);

        } catch (error) {

            console.error(error);
        }
    };

    useEffect(() => {

        fetchFailedJobs();

        const interval =
            setInterval(fetchFailedJobs, 5000);

        return () => clearInterval(interval);

    }, []);

    return (

        <div className="bg-red-100 p-4 rounded-xl shadow-md mb-6">

            <h2 className="text-xl font-bold text-red-700 mb-3">

                Failed Job Notifications

            </h2>

            {failedJobs.length === 0 ? (

                <p className="text-green-600">

                    No failed jobs 🎉

                </p>

            ) : (

                failedJobs.map((job) => (

                    <div
                        key={job.id}
                        className="bg-red-500 text-white p-3 rounded mb-2"
                    >

                        ⚠️ Job "{job.name}" failed after retries.

                    </div>
                ))
            )}
        </div>
    );
}

export default FailedNotifications;