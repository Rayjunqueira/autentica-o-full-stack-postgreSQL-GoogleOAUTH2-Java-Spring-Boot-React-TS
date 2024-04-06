import { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

const GoogleAuthRedirect = () => {
    const auth = useContext(AuthContext);
    const navigate = useNavigate();
    const { id, name, email, password } = useParams();

    useEffect(() => {
        const getAuth = async () => {
            if (email && password && id && name) {
                const isLogged = await auth.signinGoogle(id, name, email, password);
                if (isLogged) {
                    navigate('/');
                } else {
                    alert("Não funcionou!");
                }
            } else {
                console.log("Não foram fornecidos todos os parâmetros necessários.");
            }
        }; 
        getAuth();
    }, [auth, navigate, email, password, id, name]);

    return (
        <div>
            <h3>Autenticando...</h3>
        </div>
    );
};

export default GoogleAuthRedirect;
