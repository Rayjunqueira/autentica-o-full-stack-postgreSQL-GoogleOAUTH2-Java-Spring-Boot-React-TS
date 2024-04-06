import * as C from './styles';

import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import GoogleIcon from '@mui/icons-material/Google';
import { useApi } from '../../hooks/useApi';
import { AuthContext } from '../../context/AuthContext';

type Props = {}

const Authpage = (props: Props) => {
    const navigate = useNavigate();
    const api = useApi();
    const auth = useContext(AuthContext);

    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [emailError, setEmailError] = useState<string>('');
    const [passwordError, setPasswordError] = useState<string>('');

    const handleGoogleLogin = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    };

    const handleSignin = async () => {
        if (!email.trim()) {
            setEmailError("E-mail deve ser preenchido!");
            return;
        }

        if (!password.trim()) {
            setPasswordError("Senha deve ser preenchida!");
            return;
        }

        try {
            const login = await api.authenticateUser(email, password);
            if (login.success) {
                const isLogged = await auth.signin(email, password);
                if (isLogged) {
                    navigate('/');
                } else {
                    alert("Não funcionou!");
                }
            } else {
                alert("Credenciais inválidas!");
            }
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <C.Container>
            <C.MobileInputs>
                <C.LoginAnnouncement>
                    <h3>Digite seu login</h3>
                </C.LoginAnnouncement>
                <C.MobileInput>
                    <h3>Digite seu e-mail</h3>
                    <input
                        type="email"
                        placeholder='Digite seu e-mail...'
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />
                    {emailError && <p style={{ color: 'red' }}>{emailError}</p>}
                </C.MobileInput>
                <C.MobileInput>
                    <h3>Digite sua senha</h3>
                    <input
                        placeholder='Senha...'
                        value={password}
                        type='password'
                        onChange={e => setPassword(e.target.value)}
                    />
                    {passwordError && <p style={{ color: 'red' }}>{passwordError}</p>}
                </C.MobileInput>
                <C.SignupInput>
                    <a href="/signup" id='signup'>Cadastre-se aqui</a>
                </C.SignupInput>
                <C.ButtonContainer>
                    <button type='submit' onClick={handleSignin}>Entrar</button>
                </C.ButtonContainer>
                <C.OAuthContainer>
                    <button onClick={handleGoogleLogin}>
                        <C.OAuthContent>
                            Google login
                            <GoogleIcon />
                        </C.OAuthContent>
                    </button>
                </C.OAuthContainer>
            </C.MobileInputs>
        </C.Container>
    )
}

export default Authpage;
