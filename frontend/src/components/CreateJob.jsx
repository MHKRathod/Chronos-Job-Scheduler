import { useContext, useState } from "react";
import API from "../api";
import { AuthContext } from "../context/AuthContext";

function CreateJob() {

    const { auth } = useContext(AuthContext);

    const [jobName, setJobName] = useState("");

    const createJob = async () => {

        try {

            await API.post(
                "/jobs",
                {
                    name: jobName,
                    status: "PENDING"
                },
                {
                    auth: {
                        username: auth.username,
                        password: auth.password
                    }
                }
            );

            alert("Job Created");

        } catch (error) {

            alert("Failed");
        }
    };

    return (

        <div>

            <input
                type="text"
                placeholder="Job Name"
                onChange={(e) => setJobName(e.target.value)}
            />

            <button onClick={createJob}>
                Create Job
            </button>

        </div>
    );
}

export default CreateJob;