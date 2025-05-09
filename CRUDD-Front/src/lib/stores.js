import { writable } from "svelte/store";

export const tasksStore = writable([]);  // ✅ Global store for tasks
