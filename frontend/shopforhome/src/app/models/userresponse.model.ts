export interface UserResponse {
    userId: number;
    username: string;
    password: string;
    email: string;
    address?: string;
    phoneNumber?: string;
    role: string;
}
