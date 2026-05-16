import { useContext, useState } from "react";

import API from "../services/api";

import { AuthContext } from "../context/AuthContext";

function JobForm() {

    const { auth } = useContext(AuthContext);

    const [job, setJob] = useState({
        name: "",
        command: "",
        scheduledTime: "",
        cronExpression: ""
    });

    const handleChange = (e) => {

        setJob({
            ...job,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await API.post(

                "/jobs",

                job,

                {
                    auth: {
                        username: auth.username,
                        password: auth.password
                    }
                }
            );

            alert("Job Created!");

            setJob({
                name: "",
                command: "",
                scheduledTime: "",
                cronExpression: ""
            });

        } catch (error) {

            console.error(error);

            alert("Failed to create job");
        }
    };

    return (

        <div className="bg-white shadow-lg rounded-xl p-6">

            <h2 className="text-2xl font-bold bg-gradient-to-r from-blue-600 to-cyan-500 text-white px-6 py-3 rounded-xl shadow-md">
                Create Job
            </h2>

            <form onSubmit={handleSubmit} className="space-y-4">

                <input
                    type="text"
                    name="name"
                    placeholder="Job Name"
                    value={job.name}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <input
                    type="text"
                    name="command"
                    placeholder="Command"
                    value={job.command}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <input
                    type="datetime-local"
                    name="scheduledTime"
                    value={job.scheduledTime}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <input
                    type="text"
                    name="cronExpression"
                    placeholder="Example: 0 */5 * * * *"
                    value={job.cronExpression}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <button
                    type="submit"
                    className="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700"
                >
                    Create Job
                </button>

            </form>

        </div>
    );
}

export default JobForm;