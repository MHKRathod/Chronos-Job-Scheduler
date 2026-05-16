import { useContext } from "react";

import {

    Navigate

} from "react-router-dom";

import {

    AuthContext

} from "../context/AuthContext";

function ProtectedRoute({ children }) {

    const { auth } =
        useContext(AuthContext);

    if (!auth.username) {

        return <Navigate to="/login" />;
    }

    return children;
}

export default ProtectedRoute;