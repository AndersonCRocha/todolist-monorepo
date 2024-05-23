import { HTMLAttributes, Ref, forwardRef, useEffect } from "react";
import { cn } from "../utils/utils";

interface OverlayProps extends HTMLAttributes<HTMLDivElement> {
  isLeaving: boolean;
}

export const Overlay = forwardRef(
  (
    { isLeaving, className, ...rest }: OverlayProps,
    ref: Ref<HTMLDivElement>,
  ) => {
    useEffect(() => {
      document.body.style.overflow = "hidden";

      return () => {
        document.body.style.overflow = "auto";
      };
    }, []);

    return (
      <div
        ref={ref}
        data-leaving={isLeaving}
        className={cn(
          "animate-fade-in data-[leaving=true]:animate-fade-out fixed inset-0 z-[100] flex items-center justify-center bg-black/60",
          className,
        )}
        {...rest}
      />
    );
  },
);
