import { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

import API from "../services/api";

import { AuthContext } from "../context/AuthContext";

function Login() {

    const [username, setUsername] = useState("");

    const [password, setPassword] = useState("");

    const { setAuth } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async (e) => {

        e.preventDefault();

        try {

            await API.get(

                "/jobs",

                {
                    auth: {
                        username,
                        password
                    }
                }
            );

            setAuth({
                username,
                password
            });

            alert("Login Successful");
            navigate("/dashboard");

        } catch (error) {

            console.error(error);

            alert("Invalid Credentials");
        }
    };

    return (

        <div className="bg-white shadow-lg rounded-xl p-6">

            <h2 className="text-2xl font-bold bg-gradient-to-r from-purple-600 to-pink-500 text-white px-6 py-3 rounded-xl shadow-md">
                Login
            </h2>

            <form
                onSubmit={handleLogin}
                className="space-y-4"
            >

                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) =>
                        setUsername(e.target.value)
                    }
                    className="w-full border p-3 rounded-lg"
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) =>
                        setPassword(e.target.value)
                    }
                    className="w-full border p-3 rounded-lg"
                />

                <button
                    type="submit"
                    className="bg-purple-600 text-white px-6 py-3 rounded-lg hover:bg-purple-700"
                >
                    Login
                </button>

                <p className="text-center">

    Don't have an account?

    <Link
        to="/signup"
        className="text-blue-600 ml-2"
    >
        Signup
    </Link>

</p>

            </form>

        </div>
    );
}

export default Login;