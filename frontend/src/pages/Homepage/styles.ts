import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-start; 
  min-height: 100vh;
`;

export const MobileContainer = styled.div`
  width: 100%; 
  padding: 0 20px;
  box-sizing: border-box;
`;

export const WelcomeContainer = styled.div`
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
  width: 100%; 
  border-radius: 7px; 
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #d9d9d9;
`;

export const WelcomeTitles = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px 0; 
`;

export const TaskContainer = styled.div`
box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
width: 100%; 
  border-radius: 7px; 
  overflow: hidden;
  margin-top: 20px; 
  text-align: center;
`;

export const TaskTitles = styled.div`
  padding: 20px; 
  box-sizing: border-box; 
  word-wrap: break-word; 
`;

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 2vh;
`;

export const TableHead = styled.thead`
  background-color: #f2f2f2;
`;

export const TableBody = styled.tbody``;

export const TableRow = styled.tr`
  cursor: pointer;
`;

export const TableHeaderCell = styled.th`
  padding: 10px;
  text-align: left;
`;

export const TableCell = styled.td`
  padding: 10px;
  border-top: 1px solid #ddd;
  text-align: left;
`;

export const TableCellHorario = styled.td`
  padding: 10px;
  border-top: 1px solid #ddd;
  text-align: left;
`;
