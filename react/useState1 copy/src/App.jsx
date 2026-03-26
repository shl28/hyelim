import { useRef, useCallback, useReducer } from "react";
import UserList from "./UserList";
import CreateUser from "./CreateUser";

const initialState = {
    inputs: {
        username: "",
        email: "",
    },
    users: [
        {
            id: 1,
            username: "velopert",
            email: "public.velopert@gmail.com",
        },
        {
            id: 2,
            username: "tester",
            email: "tester@example.com",
        },
        {
            id: 3,
            username: "liz",
            email: "liz@example.com",
        },
    ],
};

function reducer(state, action) {
    switch (action.type) {
        case "CHANGE":
            return {
                ...state,
                inputs: {
                    ...state.inputs,
                    [action.name]: action.value,
                },
            };

        case "CREATE":
            return {
                ...state,
                users: [...state.users, action.user],
                inputs: { username: "", email: "" },
            };

        case "REMOVE":
            return {
                ...state,
                users: state.users.filter((user) => user.id !== action.id),
            };

        case "TOGGLE":
            return {
                ...state,
                users: state.users.map((user) =>
                    user.id === action.id
                        ? { ...user, active: !user.active }
                        : user,
                ),
            };

        default:
            return state;
    }
}

function App() {
    const [state, dispatch] = useReducer(reducer, initialState);

    const { inputs, users } = state;
    const { username, email } = inputs;

    const onChange = useCallback((e) => {
        const { name, value } = e.target;

        dispatch({
            type: "CHANGE",
            name,
            value,
        });
    }, []);

    const nextId = useRef(4);

    const onCreate = useCallback(() => {
        dispatch({
            type: "CREATE",
            user: {
                id: nextId.current,
                username,
                email,
            },
        });
        nextId.current += 1;
    }, [username, email]);

    const onRemove = useCallback((id) => {
        dispatch({
            type: "REMOVE",
            id,
        });
    }, []);

    const onToggle = useCallback((id) => {
        dispatch({
            type: "TOGGLE",
            id,
        });
    }, []);

    return (
        <div>
            <CreateUser
                username={username}
                email={email}
                onChange={onChange}
                onCreate={onCreate}
            />
            <UserList users={users} onRemove={onRemove} onToggle={onToggle} />
        </div>
    );
}

export default App;
