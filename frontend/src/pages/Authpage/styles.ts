import styled from 'styled-components';

export const Container = styled.div `
    @media (max-width: 982px) {
        margin-top: 16vh;
    }
`;

export const LoginAnnouncement = styled.div `
    h3 {
        font-size: 3.7vh;
    }
`;

export const MobileInputs = styled.div `
    text-align: center;    
    margin: 0 30vh;
    border-radius: 1.6vh;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    margin-top: 16vh;

  @media (max-width: 982px) {
    margin: 0 10vh;
  }

  @media (max-width: 589px) {
    margin: 0 10vh;
  }

  @media (max-width: 517px) {
    margin: 0 5vh;
  }
  
  @media (max-width: 411px) {
    margin: 0 1.5vh;
  }
`;

export const SignupInput = styled.div `
  margin-top: 2vh;
`;

export const MobileInput = styled.div `
    text-align: center;
    justify-content: center;
    h3 {
        margin-top: 3vh;
        font-size: 2.1vh;
    }

    input {
        margin-top: -5vh;
        height: 3.6vh;
        width: 28vh;
        border-radius: 1.2vh;
    }
`;

export const ButtonContainer = styled.div `
    button {
        margin-top: 3vh;
        margin-bottom: 3vh;
        width: 15vh;
        height: 4vh;
        cursor: pointer;
        background-color: #0097b2;
        color: #fff;
    }
`;

export const OAuthContainer = styled.div `
    display: flex;
    align-items: center;
    justify-content: center; 

    button {
        background-color: #ff5757;
        color: #fff;
        width: 18vh;
        height: 5vh;
        cursor: pointer;
        margin-top: 3vh;
        margin-bottom: 3vh;
        cursor: pointer;
        text-align: center;
    }
`;

export const OAuthContent = styled.div `
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 5px;
`;