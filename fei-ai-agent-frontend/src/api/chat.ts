import axios, { AxiosInstance } from 'axios'

const api: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8123/api',
  timeout: 20000
})

export default api 