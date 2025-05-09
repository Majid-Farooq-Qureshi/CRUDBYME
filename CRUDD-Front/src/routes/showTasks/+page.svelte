<script>
  export let data;
  import { goto } from "$app/navigation";
  import { tick } from "svelte";
  import "../../app.css";;  // Importing the global CSS file


  async function deleteTask(id) {
      try {
          const response = await fetch(`http://localhost:9090/tasks/${id}`, {
              method: 'DELETE'
          });

          if (!response.ok) throw new Error(`Failed to delete: ${response.status}`);

          // Ensure reactivity by reassigning a new array reference
          data = { ...data, tasks: data.tasks.filter(t => t.id !== id) };
          await tick(); // Ensure UI updates properly
      } catch (err) {
          console.error('Delete error:', err);
      }
  }
</script>

<main>
  <h1>📌 Task List</h1>
  <div>
      <ul>
          {#each data.tasks as task (task.id)}
              <li>
                  <div>
                      <p>{task.title}</p>
                      <p>{task.completed ? "✅ Completed" : "❌ Pending"}</p>
                  </div>
                  <div>
                      <button on:click={() => deleteTask(task.id)}>🗑 Delete</button>
                      <button on:click={() => goto(`/updateTasks/${task.id}`)}>✏️ Edit</button>
                  </div>
              </li>
          {/each}
      </ul>
  </div>
</main>
