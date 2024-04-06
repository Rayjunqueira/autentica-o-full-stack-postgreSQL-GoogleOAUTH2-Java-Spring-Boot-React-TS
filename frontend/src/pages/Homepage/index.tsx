import { useContext, useEffect, useState } from 'react';
import * as C from './styles';
import { useNavigate } from 'react-router-dom';
import { useApi } from '../../hooks/useApi';
import { AuthContext } from '../../context/AuthContext';

type Props = {}

const Homepage = (props: Props) => {
  const navigate = useNavigate();
  const auth = useContext(AuthContext);
  const api = useApi();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const verifyAuthTK = async () => {
      const authorization = localStorage.getItem('authorization');
      if (authorization) {
        const isAuth = await api.verifyToken(authorization);
        if (isAuth.success) {
          setLoading(false);
          navigate("/");
        } 
      }
    };
    verifyAuthTK();
  }, []);

  if (loading) {
    return <div>Carregando...</div>;
  }

  return (
    <C.Container>
      Authenticated
    </C.Container>
  );
}

export default Homepage;
