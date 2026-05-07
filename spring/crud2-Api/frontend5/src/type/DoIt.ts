export interface DoIt {
    num: number;
    title: string;
    content: string;
}

export type DoItInput = Pick<DoIt, "title" | "content">;
