export enum SessionStorageKeys {
    userName = "userName",
    userId = "userId",
}

export function getSessionStorageItem(key: string) {
    return sessionStorage.getItem(key) == undefined ? null : sessionStorage.getItem(key);
}

export function setSessionStorageItem(key: string, value: string) {
    sessionStorage.setItem(key, value)
}

export function removeSessionStorageItem(key: string) {
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
