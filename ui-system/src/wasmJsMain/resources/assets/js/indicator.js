window.agrios = window.agrios || {};

window.agrios.onReady = () => {
    document.getElementById("agrios-root")?.remove();
    document.getElementById("agrios-loading")?.remove();

    const root = document.getElementById("agrios-console-root");
    if (root) { root.style.visibility = "visible" }
};
