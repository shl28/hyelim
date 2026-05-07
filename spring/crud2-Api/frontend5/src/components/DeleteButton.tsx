import { useNavigate } from "react-router-dom";
import { deleteDoit } from "../api/doitsApi";

interface DeleteButtonProps {
    num: number;
}

export default function DeleteButton({ num }: DeleteButtonProps) {
    const navigate = useNavigate();
    const onDelete = async () => {
        if (!confirm("삭제할까요?")) return;
        try {
            const res = await deleteDoit(num);
            if (res.status === 204) {
                navigate("/list", {
                    replace: true,
                    state: { msg: "삭제가 완료되었습니다." },
                });
            } else {
                alert("삭제 실패");
            }
        } catch (e) {
            alert(e instanceof Error ? e.message : String(e));
        }
    };
    return (
        <button
            type="button"
            className="btn btn-sm btn-outline-danger"
            onClick={onDelete}
        >
            삭제
        </button>
    );
}
