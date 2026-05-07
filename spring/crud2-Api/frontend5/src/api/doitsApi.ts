import axios from "axios";
import type { AxiosResponse } from "axios";
import type { DoIt, DoItInput } from "../type/DoIt";

const api = axios.create({
    baseURL: "/api/doits",
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
    },
});

function errorMessage(err: unknown): string {
    if (!axios.isAxiosError(err)) {
        return err instanceof Error ? err.message : String(err);
    }
    const data = err.response?.data;
    if (typeof data === "string" && data.trim()) return data;
    return err.response?.statusText || err.message;
}

export async function fetchDoitList(): Promise<DoIt[]> {
    try {
        const res = await api.get<DoIt[]>("");
        return res.data;
    } catch (e) {
        throw new Error(errorMessage(e), { cause: e });
    }
}

export async function fetchDoit(num: number | string): Promise<DoIt | null> {
    try {
        const res = await api.get<DoIt>(`/${num}`);
        return res.data;
    } catch (e) {
        if (axios.isAxiosError(e) && e.response?.status === 404) return null;
        throw new Error(errorMessage(e), { cause: e });
    }
}

export async function createDoit({ title, content }: DoItInput): Promise<DoIt> {
    try {
        const res = await api.post<DoIt>("", { title, content });
        return res.data;
    } catch (e) {
        throw new Error(errorMessage(e), { cause: e });
    }
}

export async function updateDoit(
    num: number | string,
    { title, content }: DoItInput,
): Promise<DoIt> {
    try {
        const res = await api.put<DoIt>(`/${num}`, { title, content });
        return res.data;
    } catch (e) {
        throw new Error(errorMessage(e), { cause: e });
    }
}

/** 삭제 결과는 호출부에서 status로 판단합니다. */
export async function deleteDoit(num: number | string): Promise<AxiosResponse> {
    try {
        return await api.delete(`/${num}`);
    } catch (e) {
        throw new Error(errorMessage(e), { cause: e });
    }
}
