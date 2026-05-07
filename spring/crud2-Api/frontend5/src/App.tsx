import { Route, Routes } from "react-router-dom";
import Layout from "./components/Layout";
import ListPage from "./pages/ListPage";
import DetailPage from "./pages/DetailPage";
import AddPage from "./pages/AddPage";
import EditPage from "./pages/EditPage";

export default function App() {
    return (
        <Layout>
            <Routes>
                <Route path="/" element={<ListPage />} />
                <Route path="/list" element={<ListPage />} />
                <Route path="/list/:num" element={<DetailPage />} />
                <Route path="/list/:num/edit" element={<EditPage />} />
                <Route path="/mains/add" element={<AddPage />} />
            </Routes>
        </Layout>
    );
}
