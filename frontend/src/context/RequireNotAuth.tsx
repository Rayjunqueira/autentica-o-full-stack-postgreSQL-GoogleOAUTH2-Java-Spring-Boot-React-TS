import { useContext } from "react"
import { AuthContext } from "./AuthContext"
import { InvalidRoute } from "../App";
import { Navigate } from "react-router-dom";

export const RequireNotAuth = ({ children }: { children: JSX.Element }) => {
    const auth = useContext(AuthContext);

    if (auth.user) {
        return <Navigate to="/" replace />;
    }

    return children;
}