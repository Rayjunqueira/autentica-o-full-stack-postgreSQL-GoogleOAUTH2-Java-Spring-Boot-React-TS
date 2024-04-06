import { createContext } from "react";

export type User = {
    name: string;
    email: string;
    password: string;
}

export type AuthContextType = {
    user: User | null;
    token: string | null;
    signin: (email: string, password: string) => Promise<boolean>;
    signinGoogle: (token: string, name: string, email: string, password: string) => Promise<boolean>; 
    updateUser: (loggedIn: boolean) => void;
}

export const AuthContext = createContext<AuthContextType>(null!);