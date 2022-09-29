export interface User {
  id: string;
  firstname: string;
  lastname: string;
  username: string;
  email: string;
}

export interface CurrentUser {
  firstname: string;
  lastname: string;
  username: string;
  email: string;
  accessToken: string;
}
