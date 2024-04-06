import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:8080"
}); 

export const useApi = () => ({
    authLoginGoogle: async () => {
        const response = await api.get('/oauth2/redirect');
        return response.data;
    },
    verifyToken: async (token: string) => { 
        try {
            const response = await api.get('/verifyToken', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return { success: true, data: response.data };
        } catch (err) {
            console.error("Erro ao verificar o token:", err);
            return { success: false, error: "Erro ao verificar o token. Por favor, tente novamente."};
        }
    },
    authenticateUser: async (email: string, password: string) => {
        try {
            const response = await api.post('/auth', { email, password });
            return { success: true, data: response.data };
        } catch (err) {
            console.error("Erro ao entrar na sua conta:", err);
            return { success: false, error: "Não foi possível logar. Por favor, tente novamente."};
        }
    },    
    signUp: async (name: string, email: string, password: string) => {
        try {
            const response = await api.post('/user', { name, email, password });
            return { success: true, data: response.data };
        } catch (err) {
            console.error("Erro ao criar uma conta:", err);
            return { success: false, error: "Não foi possível registrar. Por favor, tente novamente."};
        }
    },       
    checkEmail: async (email: string) => {
        try {
            const response = await api.get('/user/check-email', {
                params: {
                    email: email
                }
            });
            return { success: true, data: response.data };
        } catch (err) {
            console.error("Erro a verificar email:", err);
            return { success: false, error: "Não foi possível verificar esse email. Por favor, tente novamente."};
        }
    },
    googleAuthenticate: async () => {
        try {
            const response = await api.get('/');
            return { success: true, data: response.data };
        } catch (err) {
            console.error("Erro ao entrar na sua conta google:", err);
            return { success: false, error: "Não foi possível logar google. Por favor, tente novamente."};
        }
    },
});