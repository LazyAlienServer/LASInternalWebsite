export interface UserInfo {
  id: number;
  time: number;
  userName: string;
  minecraftName: string;
  userSignature: string;
  userTag: string; //[[]] json
  lastLogin: number;
  whiteList: string; //[] json
  online: number;
  probation: number;
  administrator: number;
  qq: number;
  avatar: string;

  [key: string]: any;
}
