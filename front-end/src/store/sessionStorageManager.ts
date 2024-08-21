export enum SessionStorageKeys {
    userName = "userName",
    userId = "userId",
    avatar = "avatar",
}

export function getSessionStorageItem(key: SessionStorageKeys) {
    return sessionStorage.getItem(key) == undefined ? null : sessionStorage.getItem(key);
}

export function setSessionStorageItem(key: SessionStorageKeys, value: string) {
    sessionStorage.setItem(key, value)
}

export function removeSessionStorageItem(key: SessionStorageKeys) {
    sessionStorage.removeItem(key)
}

export function clearSessionStorage() {
    sessionStorage.clear()
}

export function getSessionStorageLength() {
    return sessionStorage.length
}

export function getKey(index: number) {
    return sessionStorage.key(index)
}
