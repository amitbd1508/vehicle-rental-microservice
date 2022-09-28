export interface User {
  id: string;
  name: string;
  email: string;
  createdAt: Date;
  updatedAt: Date;
}

export interface CurrentUser {
  firstname: string;
  lastname: string;
  username: string;
  email: string;
  token: string;
}
