import MainContent from "./components/MainContent";
import Header from "./components/Header";
import "./App.css";
import AppProvider from "./context/AppContext";

function App() {
    return (
        <div className="app">
            <AppProvider>
                <Header />
                <MainContent />
            </AppProvider>
        </div>
    );
}

export default App;

// Context -> 전역 데이터 통로
// Provider -> 데이터 공급자
// useContext -> 데이터 꺼내기
