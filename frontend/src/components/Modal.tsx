import { ClassValue } from "clsx";
import { X } from "lucide-react";
import { ReactNode, useEffect } from "react";
import { useAnimatedUnmount } from "../hooks/use-animated-unmounted";
import { cn } from "../utils/utils";
import { Button } from "./Button";
import { Overlay } from "./Overlay";
import { ReactPortal } from "./ReactPortal";

export interface ModalProps {
  visible: boolean;
  title: string | ReactNode;
  cancelLabel?: string;
  confirmLabel?: string;
  danger?: boolean;
  isLoading?: boolean;
  children: ReactNode;
  showCancelButton?: boolean;
  showConfirmButton?: boolean;
  confirmClassName?: string;
  containerClassName?: ClassValue;
  bodyClassName?: ClassValue;
  footerClassName?: ClassValue;
  footerElement?: ReactNode;
  showFooter?: boolean;
  onCancel?: () => void;
  onClose: () => void;
  onConfirm: () => void;
}

export function Modal({
  visible,
  title,
  danger,
  cancelLabel = "Cancelar",
  confirmLabel = "Confirmar",
  children,
  isLoading = true,
  showCancelButton = true,
  showConfirmButton = true,
  confirmClassName,
  containerClassName,
  bodyClassName,
  footerClassName,
  footerElement,
  showFooter = true,
  onClose,
  onCancel,
  onConfirm,
}: ModalProps) {
  const { shouldRender, animatedElementRef } =
    useAnimatedUnmount<HTMLDivElement>(visible);

  useEffect(() => {
    function handleKeyUpEsc(event: KeyboardEvent) {
      if (event.key === "Escape") {
        onClose();
      }
    }

    window.addEventListener("keyup", handleKeyUpEsc);

    return () => {
      window.removeEventListener("keyup", handleKeyUpEsc);
    };
  }, [onClose]);

  if (!shouldRender) {
    return null;
  }

  return (
    <ReactPortal containerId="modal-root">
      <Overlay isLeaving={!visible} ref={animatedElementRef}>
        <div
          data-leaving={!visible}
          className={cn(
            "shadow-modal data-[leaving=true]:animate-scale-out relative mx-4 grid max-h-[90%] w-full max-w-lg grid-rows-[auto_1fr_auto] rounded-lg bg-white p-6 pt-6",
            containerClassName,
          )}
        >
          <X
            size={32}
            onClick={onClose}
            className="absolute right-2 top-2 cursor-pointer rounded-full p-1 text-secondary-600 transition-colors hover:bg-zinc-200"
          />

          <h1 className="text-xl font-semibold text-primary-900">{title}</h1>

          <div className={cn("-m-1 mt-6 overflow-x-auto p-1", bodyClassName)}>
            {children}
          </div>

          {showFooter && (
            <footer
              className={cn(
                "mt-6 flex items-center justify-end gap-2",
                footerClassName,
              )}
            >
              {footerElement}

              {showCancelButton && (
                <button
                  type="button"
                  className="font-base mr-4 h-12 border-none bg-transparent text-zinc-600 disabled:cursor-not-allowed disabled:text-zinc-400"
                  disabled={isLoading}
                  onClick={onCancel}
                >
                  {cancelLabel}
                </button>
              )}

              {showConfirmButton && (
                <Button
                  type="button"
                  onClick={onConfirm}
                  isLoading={isLoading}
                  className={confirmClassName}
                  variant={danger ? "danger" : undefined}
                >
                  {confirmLabel}
                </Button>
              )}
            </footer>
          )}
        </div>
      </Overlay>
    </ReactPortal>
  );
}
