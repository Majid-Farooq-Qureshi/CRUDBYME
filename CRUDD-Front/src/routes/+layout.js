export async function load({ fetch }) {
    const response = await fetch('http://localhost:9090/tasks');
    const tasks = await response.json();
    return { tasks };
  } 