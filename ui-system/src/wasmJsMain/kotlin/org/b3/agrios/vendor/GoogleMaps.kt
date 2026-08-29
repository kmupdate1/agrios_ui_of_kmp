package org.b3.agrios.vendor

import org.w3c.dom.HTMLDivElement

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """(element, apiKey) => {
        const callbackName = "__agriosInitGoogleMaps";

        window[callbackName] = () => {
            const googleMaps = window.google?.maps;

            if (!googleMaps) {
                console.error("Google Maps API is not available.");
                return;
            }

            new googleMaps.Map(element, {
                center: {
                    lat: 35.3983,
                    lng: 136.8485
                },
                zoom: 15
            });
        };

        const script = document.createElement("script");

        script.src =
            "https://maps.googleapis.com/maps/api/js" +
            "?key=" + encodeURIComponent(apiKey) +
            "&loading=async" +
            "&libraries=maps" +
            "&callback=" + callbackName;

        script.async = true;

        script.onerror = () => {
            console.error("Failed to load Google Maps API.");
        };

        document.head.appendChild(script);
    }"""
)
internal external fun createGoogleMaps(
    element: HTMLDivElement,
    apiKey: String,
)
