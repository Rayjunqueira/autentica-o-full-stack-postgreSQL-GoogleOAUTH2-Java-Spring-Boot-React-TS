import { useEffect, useState } from "react"
import { useApi } from "../hooks/useApi";
import { AuthContext } from "./AuthContext";

export type User = {
    name: string;
    email: string;
    password: string;
}

export const AuthProvider = ({ children }: { children: JSX.Element }) => {
    const [user, setUser] = useState<User | null>(null);
    const [token, setToken] = useState<string | null>(null); 

    const api = useApi();


    useEffect(() => {
        const validateToken = async () => {
            try {
                const storageData = localStorage.getItem('authorization');
                if (storageData) {
                    const data = await api.verifyToken(storageData);
                    if (data.success) { 
                        setUser(data.data); 
                        setToken(storageData);
                    } else { 
                        console.log(data.error);
                    }  
                }
            } catch(err) {  
                console.log(err);
            }
        }
        validateToken();
    }, []);


    const signin = async (email: string, password: string) => {
        const data = await api.authenticateUser(email, password);
        if (data.data) {
            setUser(data.data.user);
            setToken(data.data.token);
            localStorage.setItem('authorization', data.data.token); 

            return true;            
        } 
        return false;
    }

    const signinGoogle = async (token: string, name: string, email: string, password: string) => {
        const data = await api.verifyToken(token);
        const user: User = { name, email, password };
        if (data.success) {
            setUser(user);
            setToken(token);
                localStorage.setItem('authorization', token); 

            return true;            
        } 
        return false;
    }

    const updateUser = (loggedIn: boolean) => {
        if (loggedIn) {
        } else {
            setUser(null);
            setToken(null);
            localStorage.removeItem('authorization');
        }
    };

    return (
        <AuthContext.Provider value={{ user, token, signin, signinGoogle, updateUser }}>
            { children }
        </AuthContext.Provider>
    );
}