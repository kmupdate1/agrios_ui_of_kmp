window.agrios = window.agrios || {};

window.agrios.onReady = () => {
    requestAnimationFrame(() => {
        requestAnimationFrame(() => {
            document.getElementById("agrios-loading")?.remove();

            const root = document.getElementById("agrios-console-root");
            if (root) { root.style.visibility = "visible"; }
        });
    });
};
