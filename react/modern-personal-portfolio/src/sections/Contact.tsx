import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';
import { motion } from 'motion/react';
import { Send } from 'lucide-react';
import { Section } from '../components/Section';
import { Button } from '../components/Button';
import { SOCIAL_LINKS } from '../constants';

const contactSchema = z.object({
  name: z.string().min(2, 'Name must be at least 2 characters'),
  email: z.string().email('Invalid email address'),
  message: z.string().min(10, 'Message must be at least 10 characters'),
});

type ContactFormData = z.infer<typeof contactSchema>;

export const Contact = () => {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
    reset,
  } = useForm<ContactFormData>({
    resolver: zodResolver(contactSchema),
  });

  const onSubmit = async (data: ContactFormData) => {
    // Simulate API call
    await new Promise((resolve) => setTimeout(resolve, 1000));
    console.log('Form submitted:', data);
    alert('Message sent successfully!');
    reset();
  };

  return (
    <Section id="contact" title="Get in Touch" subtitle="Have a project in mind? Let's talk about it.">
      <div className="grid gap-12 lg:grid-cols-2">
        {/* Contact Info */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
        >
          <h3 className="text-2xl font-bold text-zinc-900 dark:text-white">Let's connect</h3>
          <p className="mt-4 text-lg text-zinc-600 dark:text-zinc-400">
            I'm always open to discussing new projects, creative ideas or opportunities to be part of your visions.
          </p>
          
          <div className="mt-8 flex gap-4">
            {SOCIAL_LINKS.map((social) => (
              <a
                key={social.label}
                href={social.href}
                className="rounded-full bg-zinc-100 p-3 text-zinc-600 transition-all hover:bg-zinc-900 hover:text-white dark:bg-zinc-800 dark:text-zinc-400 dark:hover:bg-white dark:hover:text-zinc-900"
                aria-label={social.label}
              >
                <social.icon size={24} />
              </a>
            ))}
          </div>

          <div className="mt-12 space-y-4">
            <div className="flex items-center gap-4 text-zinc-600 dark:text-zinc-400">
              <div className="h-10 w-10 rounded-full bg-zinc-100 flex items-center justify-center dark:bg-zinc-800">
                <Send size={20} />
              </div>
              <span>hello@example.com</span>
            </div>
          </div>
        </motion.div>

        {/* Contact Form */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="rounded-2xl border border-zinc-200 bg-white p-8 dark:border-zinc-800 dark:bg-zinc-900"
        >
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-zinc-700 dark:text-zinc-300">Name</label>
              <input
                {...register('name')}
                className="mt-1 block w-full rounded-lg border border-zinc-200 bg-zinc-50 px-4 py-2.5 text-zinc-900 focus:border-zinc-900 focus:ring-0 dark:border-zinc-800 dark:bg-zinc-950 dark:text-white dark:focus:border-white"
                placeholder="Your name"
              />
              {errors.name && <p className="mt-1 text-xs text-red-500">{errors.name.message}</p>}
            </div>

            <div>
              <label className="block text-sm font-medium text-zinc-700 dark:text-zinc-300">Email</label>
              <input
                {...register('email')}
                className="mt-1 block w-full rounded-lg border border-zinc-200 bg-zinc-50 px-4 py-2.5 text-zinc-900 focus:border-zinc-900 focus:ring-0 dark:border-zinc-800 dark:bg-zinc-950 dark:text-white dark:focus:border-white"
                placeholder="your@email.com"
              />
              {errors.email && <p className="mt-1 text-xs text-red-500">{errors.email.message}</p>}
            </div>

            <div>
              <label className="block text-sm font-medium text-zinc-700 dark:text-zinc-300">Message</label>
              <textarea
                {...register('message')}
                rows={4}
                className="mt-1 block w-full rounded-lg border border-zinc-200 bg-zinc-50 px-4 py-2.5 text-zinc-900 focus:border-zinc-900 focus:ring-0 dark:border-zinc-800 dark:bg-zinc-950 dark:text-white dark:focus:border-white"
                placeholder="How can I help you?"
              />
              {errors.message && <p className="mt-1 text-xs text-red-500">{errors.message.message}</p>}
            </div>

            <Button type="submit" className="w-full" disabled={isSubmitting}>
              {isSubmitting ? 'Sending...' : 'Send Message'}
            </Button>
          </form>
        </motion.div>
      </div>
    </Section>
  );
};
