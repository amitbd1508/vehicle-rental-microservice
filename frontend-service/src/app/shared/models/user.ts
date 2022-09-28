export interface User {
  id: string;
  name: string;
  email: string;
  createdAt: Date;
  updatedAt: Date;
}

export interface CurrentUser {
  name: string;
  email: string;
  token: string;
}
