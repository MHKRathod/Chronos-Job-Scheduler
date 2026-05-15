import axios from "axios";

const API = axios.create({

    baseURL: "http://localhost:8080/jobs",

    auth: {
        username: "admin",
        password: "admin123"
    }
});

export default API;