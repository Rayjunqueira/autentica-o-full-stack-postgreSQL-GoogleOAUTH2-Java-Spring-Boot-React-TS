import * as C from './styles';

import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useApi } from '../../hooks/useApi';

type Props = {}

const Signuppage = (props: Props) => {
  const api = useApi();
  const navigate = useNavigate();

  const [name, setName] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [repeatPassword, setRepeatPassword] = useState<string>('');
  const [emailVerifyEmail, setEmailVerifyEmail] = useState<string>('');
 
  const [nameError, setNameError] = useState<string>('');
  const [emailError, setEmailError] = useState<string>('');
  const [passwordError, setPasswordError] = useState<string>('');
  const [repeatPasswordError, setRepeatPasswordError] = useState<string>('');
  
  const [samePasswordError, setSamePasswordError] = useState<string>('');

  const handleEmailCheck = async () => {
    const response = await api.checkEmail(email);
    if (response.success && response.data) {
        console.log("OK");
      } else {
        setEmailVerifyEmail("E-mail já está sendo utilizado!")
      }
    }

    const handleSamePassword = async () => {
        if (password === repeatPassword) {
            return true;
        } else {
            setSamePasswordError("As senhas não são iguais!");
            return false;
        }
    }

  const handlePasswordComplexity = () => {
    const pattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!pattern.test(password)) {
        setPasswordError("A senha deve conter pelo menos 8 caracteres, incluindo letras e números!");
        return false;
    }
    return true;
  }

  const handleSignUp = async () => {
    await handleEmailCheck();

    if (!name.trim()) {
        setNameError("Nome deve ser preenchido!");
        return;
    }

    if (!email.trim()) {
        setEmailError("E-mail deve ser preenchido!")
        return;
    }

    if (!password.trim()) {
        setPasswordError("Senha deve ser preenchida!")
        return;
    }

    if (!repeatPassword.trim()) {
        setRepeatPasswordError("Senha deve ser preenchida!")
        return;
    }

    const samePasswordValid = await handleSamePassword();
    if (!samePasswordValid) {
        return;
    }

    if (!handlePasswordComplexity()) {
        return;
    }

    const responseSignUp = await api.signUp(name, email, password);

    if (responseSignUp.success) {
        navigate("/auth")
    }
  }

  return (
    <C.Container>
        <C.MobileInputs>
            <C.LoginAnnouncement>
                <h3>Registre uma conta</h3>
            </C.LoginAnnouncement>
            <C.MobileInput>
                <h3>Digite seu nome</h3>
                <input
                    type="text" 
                    placeholder='Nome...' 
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
                {nameError && <p style={{ color: 'red' }}>{nameError}</p>} 
            </C.MobileInput>
            <C.MobileInput>
                <h3>Digite seu e-mail</h3>
                <input 
                    type="text" 
                    placeholder='Email...' 
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
                {emailVerifyEmail && <p style={{ color: 'red' }}>{emailVerifyEmail}</p>} 
                {emailError && <p style={{ color: 'red' }}>{emailError}</p>} 
            </C.MobileInput>
            <C.MobileInput>
                <h3>Digite sua senha</h3>
                <input 
                    type="password" 
                    placeholder='Senha...' 
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
                {passwordError && <p style={{ color: 'red' }}>{passwordError}</p>} 
            </C.MobileInput>            
            <C.MobileInput>
                <h3>Repetir sua senha</h3>
                <input 
                    type="password" 
                    placeholder='Senha...' 
                    value={repeatPassword}
                    onChange={e => setRepeatPassword(e.target.value)}
                />
                {samePasswordError && <p style={{ color: 'red' }}>{samePasswordError}</p>} 
                {repeatPasswordError && <p style={{ color: 'red' }}>{repeatPasswordError}</p>}
            </C.MobileInput>
            <C.SignupInput>
                <a href="/auth" id='signup'>Já tem uma conta? Faça login</a>
            </C.SignupInput>
            <C.ButtonContainer>
                <button onClick={handleSignUp}>Cadastrar</button>
            </C.ButtonContainer>
        </C.MobileInputs>
    </C.Container>
  )
}

export default Signuppage;
