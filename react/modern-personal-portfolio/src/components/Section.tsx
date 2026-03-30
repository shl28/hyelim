import React from 'react';
import { cn } from '../lib/utils';

interface SectionProps {
  id: string;
  title?: string;
  subtitle?: string;
  children: React.ReactNode;
  className?: string;
}

export const Section = ({ id, title, subtitle, children, className }: SectionProps) => {
  return (
    <section id={id} className={cn('py-24 px-6 md:px-12 lg:px-24', className)}>
      <div className="mx-auto max-w-6xl">
        {(title || subtitle) && (
          <div className="mb-16 text-center">
            {title && (
              <h2 className="text-3xl font-bold tracking-tight text-zinc-900 sm:text-4xl dark:text-white">
                {title}
              </h2>
            )}
            {subtitle && (
              <p className="mt-4 text-lg text-zinc-600 dark:text-zinc-400">
                {subtitle}
              </p>
            )}
          </div>
        )}
        {children}
      </div>
    </section>
  );
};
