export const load = async ({fetch, params}) => {

    const fetchTask = async (id) => {
        const res = await fetch(`http://localhost:9090/tasks/${id}`)
        const data = await res.json()
        return data
    }

    return {
        task: await fetchTask(params.id)
    }
}