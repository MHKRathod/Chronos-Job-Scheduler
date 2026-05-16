import { useState } from "react";

import API from "../services/api";
import { Link } from "react-router-dom";

function Signup() {

    const [user, setUser] = useState({
        username: "",
        password: "",
        role: "ADMIN"
    });

    const handleChange = (e) => {

        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const handleSignup = async (e) => {

        e.preventDefault();

        try {

            await API.post(
                "/auth/signup",
                user
            );

            alert("Signup Successful");

            setUser({
                username: "",
                password: "",
                role: "ADMIN"
            });

        } catch (error) {

            console.error(error);

            alert("Signup Failed");
        }
    };

    return (

        <div className="bg-white shadow-lg rounded-xl p-6">

            <h2 className="text-2xl font-bold bg-gradient-to-r from-green-600 to-emerald-500 text-white px-6 py-3 rounded-xl shadow-md">
                Signup
            </h2>

            <form
                onSubmit={handleSignup}
                className="space-y-4"
            >

                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={user.username}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={user.password}
                    onChange={handleChange}
                    className="w-full border p-3 rounded-lg"
                />

                <button
                    type="submit"
                    className="bg-green-600 text-white px-6 py-3 rounded-lg hover:bg-green-700"
                >
                    Signup
                </button>

                <p className="text-center">

    Already have an account?

    <Link
        to="/login"
        className="text-blue-600 ml-2"
    >
        Login
    </Link>

</p>

            </form>

        </div>
    );
}

export default Signup;