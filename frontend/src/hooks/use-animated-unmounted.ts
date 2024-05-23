import { useEffect, useRef, useState } from "react";

export function useAnimatedUnmount<T extends HTMLElement>(visible: boolean) {
  const [shouldRender, setShouldRender] = useState(visible);

  const animatedElementRef = useRef<T>(null);

  useEffect(() => {
    if (visible) {
      setShouldRender(true);
    }

    function handleAnimationEnd() {
      setShouldRender(false);
    }

    const elementRef = animatedElementRef.current;
    if (!visible && elementRef) {
      elementRef.addEventListener("animationend", handleAnimationEnd);
    }

    return () => {
      if (elementRef) {
        elementRef.removeEventListener("animationend", handleAnimationEnd);
      }
    };
  }, [visible]);

  return { shouldRender, animatedElementRef };
}
